import { AuthServiceStrategyModule } from '@app/auth/src/auth-service-strategy.module';
import { APP_SERVICE, AppService } from '@app/app/src/service/app.service';
import { Module } from '@nestjs/common';

@Module({
  imports: [AuthServiceStrategyModule],
  providers: [AppService],
  exports: [AppService],
})
export class AppServiceModule {}
