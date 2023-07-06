import { AuthService, Session } from '@app/auth/src/auth.service';
import { Injectable } from '@nestjs/common';

@Injectable()
export class PlaystoreAuthService implements AuthService {
  async authorize(): Promise<Session> {
    console.log('플레이스토어 인증 작업 수행');
    return Promise.resolve(undefined);
  }
}
