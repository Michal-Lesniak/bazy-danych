import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{

  public time = '';
  public date = '';

  constructor() { }

  ngOnInit() {
      this.clock();
  }

  private clock(): void {
    let state = false
    setInterval(() => {
      const date = new Date;
      const day = date.getDate()
      const month: number | string = (date.getMonth() + 1) < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1
      const year = date.getFullYear();
      const hour: number | string = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours();
      const minute: number | string = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes();
      this.date = `${day}.${month}.${year}r.`
      this.time = state ? ` ${hour}:${minute}` : `${hour} ${minute}`;
      state = !state;
    }, 1000);
  }
}
