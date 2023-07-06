import { authServiceStrategyMap } from '@app/auth/src/auth-service-strategy';
import { AmazonAuthService } from '@app/store/amazon/src/amazon-auth.service';
import { Module } from '@nestjs/common';

@Module({
  providers: [AmazonAuthService],
  exports: [AmazonAuthService],
})
export class AmazonModule {
  constructor(amazonLoginService: AmazonAuthService) {
    authServiceStrategyMap.set('AMAZON', amazonLoginService);
  }
}
