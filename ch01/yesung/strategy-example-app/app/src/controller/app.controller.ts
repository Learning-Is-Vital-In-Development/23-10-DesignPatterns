import { Store } from '@app/common/types';
import { APP_SERVICE, AppService } from '@app/app/src/service/app.service';
import { Controller, Post, Param, Inject } from '@nestjs/common';

@Controller('post')
export class AppController {
  constructor(
    private readonly appService: AppService,
  ) {}

  @Post('scraping/:store/:id')
  async scraping(@Param('store') store: Store, @Param('id') id: string) {
    this.appService.login(store, id, 'pwd');
  }
}
