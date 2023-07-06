import { authServiceStrategyMap } from '@app/auth/src/auth-service-strategy';
import { AppstoreAuthService } from '@app/store/appstore/src/appstore-auth.service';
import { Module } from '@nestjs/common';

@Module({
  providers: [AppstoreAuthService],
  exports: [AppstoreAuthService],
})
export class AppstoreModule {
  constructor(appstoreAuthService: AppstoreAuthService) {
    authServiceStrategyMap.set('APPSTORE', appstoreAuthService);
  }
}
