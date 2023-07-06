import { Injectable } from '@nestjs/common';
import { Store } from '@app/common/types';
import { AuthServiceStrategy } from '@app/auth/src/auth-service-strategy';

@Injectable()
export class AppService {
  constructor(private readonly authServiceStrategy: AuthServiceStrategy) {}

  async login(store: Store, id: string, password: string) {
    console.log(store)
    await this.authServiceStrategy.get(store).authorize({ id, password });
  }
}

export const APP_SERVICE = 'APP_SERVICE'
