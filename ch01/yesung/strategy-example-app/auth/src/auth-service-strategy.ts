import { AuthService } from '@app/auth/src/auth.service';
import { Store } from '@app/common/types';
import { Injectable } from '@nestjs/common';

export const authServiceStrategyMap = new Map<Store, AuthService>();

@Injectable()
export class AuthServiceStrategy {
  get(store: Store): AuthService {
    return authServiceStrategyMap.get(store);
  }
}
