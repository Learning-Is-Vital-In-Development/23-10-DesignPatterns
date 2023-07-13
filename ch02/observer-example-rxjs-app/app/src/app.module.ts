import { AppController } from '@app/app/src/controller/app.controller';
import { Module } from '@nestjs/common';
import { AppServiceModule } from './service/app.service.module';

@Module({
  imports: [AppServiceModule],
  controllers: [AppController],
})
export class AppModule {}
