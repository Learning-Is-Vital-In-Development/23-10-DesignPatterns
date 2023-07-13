import { ScrapingNotiObserver } from '@app/common/scrapObserver/scrap.noti.observer';
import { ScrapingSaveObserver } from '@app/common/scrapObserver/scrap.observer';
import { ScrapingSubject } from '@app/common/scrapSubject/scrap.subject';
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

  async scrap() {
    const testData = [
      { name: 'han', instagramId: 'testid1' },
      { name: 'kim', instagramId: 'testid2' },
      { name: 'lee', instagramId: 'testid3' },
    ];

    this.scraper.broadCastScraping(testData);
  }
}

export const APP_SERVICE = 'APP_SERVICE';
