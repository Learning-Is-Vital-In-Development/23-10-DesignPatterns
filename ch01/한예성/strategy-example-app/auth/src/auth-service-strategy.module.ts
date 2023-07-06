import { AuthServiceStrategy } from '@app/auth/src/auth-service-strategy';
import { Module } from '@nestjs/common';

@Module({
  providers: [AuthServiceStrategy],
  exports: [AuthServiceStrategy],
})
export class AuthServiceStrategyModule {}
