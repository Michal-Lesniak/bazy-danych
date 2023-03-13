import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class ConnectionService {

  constructor(private http: HttpClient) { }

  public checkConnection() {
    return this.http.get<boolean>('http://localhost:8080');
  }
}
