import { ScrapingNotiObserver } from '@app/common/scrapObserver/scrap.noti.observer';
import { ScrapingSaveObserver } from '@app/common/scrapObserver/scrap.observer';
import { ScrapingSubject } from '@app/common/scrapSubject/scrap.subject';
import { ScrapingData } from '@app/common/types';
import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  constructor(
    private readonly scrapSaver: ScrapingSaveObserver,
    private readonly notifier: ScrapingNotiObserver,
    private readonly scraper: ScrapingSubject,
  ) {
    this.scraper.addScrapingObserver(this.scrapSaver);
    this.scraper.addScrapingObserver(this.notifier);
  }

  async scrap(data: ScrapingData[]) {
    this.scraper.broadCastScraping(data);
  }
}

export const APP_SERVICE = 'APP_SERVICE';
