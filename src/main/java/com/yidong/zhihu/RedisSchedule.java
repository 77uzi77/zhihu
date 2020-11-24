package com.yidong.zhihu;

import com.yidong.zhihu.service.FavService;
import com.yidong.zhihu.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * redis定时任务
 */
@Component
public class RedisSchedule {
    @Autowired
    private FavService favService;
    @Autowired
    private ThumbService thumbService;

    /**
     * 定时 将 点赞、收藏 信息 保存到数据库
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void dataScheduled(){
        favService.transFavFromRedis2DB();
        thumbService.transThumbFromRedis2DB();
    }
}
