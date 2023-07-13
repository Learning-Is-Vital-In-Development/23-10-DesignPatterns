import { Injectable } from '@nestjs/common';
import { Subject, Observer, Observable, from } from 'rxjs';
import { ScrapingData } from '../types';

@Injectable()
export class ScrapingSubject {
  private readonly subject = new Subject<any>();

  addScrapingObserver(scrapingObserver: Observer<ScrapingData>) {
    this.subject.subscribe(scrapingObserver);
  }

  broadCastScraping(data: ScrapingData[]) {
    const observable: Observable<ScrapingData> = from(data);
    observable.subscribe(this.subject);
  }
}
