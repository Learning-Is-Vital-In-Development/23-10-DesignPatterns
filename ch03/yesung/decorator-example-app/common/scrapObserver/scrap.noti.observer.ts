import { Injectable } from '@nestjs/common';
import { Subject, Observer } from 'rxjs';
import { ScrapingData } from '../types';

@Injectable()
export class ScrapingNotiObserver implements Observer<ScrapingData> {
  private readonly subject = new Subject<any>();

  next(data: ScrapingData): void {
    console.log('스크래핑 노티 작업 수행 : ', JSON.stringify(data));
  }

  error(err: any): void {
    throw err;
  }

  complete(): void {
    console.log('스크래핑 노티 작업 완료!');
  }
}
