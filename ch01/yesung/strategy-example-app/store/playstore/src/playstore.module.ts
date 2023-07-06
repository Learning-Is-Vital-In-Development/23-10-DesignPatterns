import { authServiceStrategyMap } from '@app/auth/src/auth-service-strategy';
import { PlaystoreAuthService } from '@app/store/playstore/src/playstore-auth.service';
import { Module } from '@nestjs/common';

@Module({
  providers: [PlaystoreAuthService],
  exports: [PlaystoreAuthService],
})
export class PlaystoreModule {
  constructor(playstoreAuthService: PlaystoreAuthService) {
    authServiceStrategyMap.set('PLAYSTORE', playstoreAuthService);
  }
}
