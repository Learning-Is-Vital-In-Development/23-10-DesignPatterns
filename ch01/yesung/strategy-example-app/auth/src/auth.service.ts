export interface AuthService {
  authorize(param: AuthParam): Promise<Session>;
}

export type Session = {
  authToken: string;
};

export type AuthParam = {
  id: string;
  password: string;
};
