import { AppService } from '@app/app/src/service/app.service';
import { ScrapingData } from '@app/common/types';
import { Controller, Post, Param, Body } from '@nestjs/common';
import { CacheAppService } from '../service/cache-app.service copy';

@Controller('post')
export class AppController {
  constructor(private readonly appService: CacheAppService) {}

  @Post('scraping/')
  async scraping(@Body() scrapingData: ScrapingData[]) {
    this.appService.scrap(scrapingData);
  }
}
