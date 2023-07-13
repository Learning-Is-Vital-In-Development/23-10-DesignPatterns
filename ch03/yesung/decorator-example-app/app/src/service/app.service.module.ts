import { AppService } from '@app/app/src/service/app.service';
import { Module } from '@nestjs/common';
import { ScrapingNotiObserver } from '@app/common/scrapObserver/scrap.noti.observer';
import { ScrapingSaveObserver } from '@app/common/scrapObserver/scrap.observer';
import { ScrapingSubject } from '@app/common/scrapSubject/scrap.subject';
import { CacheAppService } from './cache-app.service copy';

@Module({
  providers: [
    AppService,
    CacheAppService,
    ScrapingNotiObserver,
    ScrapingSaveObserver,
    ScrapingSubject,
  ],
  exports: [AppService, CacheAppService],
})
export class AppServiceModule {}
