import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-home-tile',
  templateUrl: './home-tile.component.html',
  styleUrls: ['./home-tile.component.scss']
})
export class HomeTileComponent {

  constructor() { }

  @Input()
  public name: string = "";

  @Input()
  public link: string = "";

  @Input()
  public photo: string = "";
}
