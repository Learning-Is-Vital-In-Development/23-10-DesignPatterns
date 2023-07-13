import { AppService } from '@app/app/src/service/app.service';
import { Controller, Post, Param } from '@nestjs/common';

@Controller('post')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Post('scraping/')
  async scraping() {
    this.appService.scrap();
  }
}
