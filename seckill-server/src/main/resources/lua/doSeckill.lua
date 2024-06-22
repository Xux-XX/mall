--判断是否处于秒杀时间段
local seckillKey = KEYS[1]
if not redis.call('EXISTS', seckillKey) then
    return 1
end
local nowTime = tonumber(ARGV[1])
local beginTime = tonumber(redis.call('HGET', seckillKey, 'startTime'))
local endTime = tonumber(redis.call('HGET', seckillKey, 'endTime'))
if nowTime < beginTime or nowTime > endTime then
    return 1
end
--判断是否到达购买上限
local userKey = KEYS[2]
local limitKey = KEYS[3]
local maxCount = tonumber(redis.call('GET', limitKey))
local buyCount = tonumber(ARGV[2])
local currentCount = tonumber(redis.call('GET', userKey) or '0')
if currentCount + buyCount > maxCount then
    return 2
end
--判断库存是否足够
local stockKey = KEYS[4]
local stock = tonumber(redis.call('GET', stockKey) or '0')
if stock < buyCount then
    return 3
end
--更新库存
redis.call('INCRBY', userKey, buyCount)
redis.call('DECRBY', stockKey, buyCount)

return 0
