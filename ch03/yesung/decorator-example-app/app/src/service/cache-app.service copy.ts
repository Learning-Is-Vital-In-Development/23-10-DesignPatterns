import { ScrapingNotiObserver } from '@app/common/scrapObserver/scrap.noti.observer';
import { ScrapingSaveObserver } from '@app/common/scrapObserver/scrap.observer';
import { ScrapingSubject } from '@app/common/scrapSubject/scrap.subject';
import { ScrapingData } from '@app/common/types';
import { Injectable } from '@nestjs/common';
import { AppService } from './app.service';

@Injectable()
export class CacheAppService extends AppService {
  constructor(
    scrapSaver: ScrapingSaveObserver,
    notifier: ScrapingNotiObserver,
    scraper: ScrapingSubject,
    private readonly appService: AppService,
  ) {
    super(scrapSaver, notifier, scraper);
  }

  async scrap(scrapingData: ScrapingData[]) {
    const cacheData: ScrapingData[] = [];

    if (cacheData.length > 0) {
      this.appService.scrap(cacheData);
    } else {
      this.appService.scrap(scrapingData);
    }
  }
}

export const APP_SERVICE = 'APP_SERVICE';
