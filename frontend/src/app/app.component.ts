import { Component, OnInit } from '@angular/core';
import { ConnectionService } from './services/connection.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'bd-front';

  public backendState = false;

  constructor(private connection: ConnectionService) {}

  ngOnInit() {
    this.connection.checkConnection().subscribe(() => {
      this.backendState = true;
    });
  }
}
