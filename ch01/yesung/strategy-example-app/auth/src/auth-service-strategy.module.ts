import { AuthServiceStrategy } from '@app/auth/src/auth-service-strategy';
import { AmazonModule } from '@app/store/amazon/src/amazon.module';
import { AppstoreModule } from '@app/store/appstore/src/appstore.module';
import { PlaystoreModule } from '@app/store/playstore/src/playstore.module';
import { Module } from '@nestjs/common';

@Module({
  imports: [ AmazonModule, AppstoreModule, PlaystoreModule ],
  providers: [AuthServiceStrategy],
  exports: [AuthServiceStrategy],
})
export class AuthServiceStrategyModule {}
