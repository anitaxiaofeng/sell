package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author: Anita
 * @Date: Created in 21:23 2017/11/14
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**加锁
     *@Author Anita
     *@Data 2017/11/14 21:25
     *@param value:当前时间+过期时间
     *@return
     */
    public boolean lock(String key,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;  //如果能成功设置返回true
        }
        //为了避免死锁而加的下面代码
        String currenValue=redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currenValue)&&Long.parseLong(currenValue)<System.currentTimeMillis()){
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currenValue)){
                return true;
            }
        }
        return false;
        //if(!redisLock.lock(productId,String.valueOf(time)){
        // 这是加锁返回false的话就抛出异常，而不会去执行下面操作
        // redis是单线程，分布式锁：多台机器上多个进程对一个数据进行操作互斥，以productId为key
        // 来进行操作}
    }

    public void unlock(String key,String value){
        String currentValue = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }
}
