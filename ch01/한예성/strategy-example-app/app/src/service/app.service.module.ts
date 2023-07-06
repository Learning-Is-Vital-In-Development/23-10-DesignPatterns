import { AuthServiceStrategyModule } from '@app/auth/src/auth-service-strategy.module';
import { AppService } from '@app/app/src/service/app.service';

import { Module } from '@nestjs/common';

@Module({
  imports: [AuthServiceStrategyModule],
  providers: [AuthServiceStrategyModule],
  exports: [AppService],
})
export class AppServiceModule {}
