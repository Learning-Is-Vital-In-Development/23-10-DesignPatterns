import { AppService } from '@app/app/src/service/app.service';
import { Module } from '@nestjs/common';
import { ScrapingNotiObserver } from '@app/common/scrapObserver/scrap.noti.observer';
import { ScrapingSaveObserver } from '@app/common/scrapObserver/scrap.observer';
import { ScrapingSubject } from '@app/common/scrapSubject/scrap.subject';

@Module({
  providers: [
    AppService,
    ScrapingNotiObserver,
    ScrapingSaveObserver,
    ScrapingSubject,
  ],
  exports: [AppService],
})
export class AppServiceModule {}
