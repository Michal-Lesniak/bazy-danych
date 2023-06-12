import { Component, OnInit } from '@angular/core';
import { ConnectionService } from '../services/connection.service';
import { HomeTile } from '../interfaces/home-tile';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  public tilesData: HomeTile[] = [
    {name: "Klienci", link: "/customers", photo: "https://cdn-icons-png.flaticon.com/512/921/921347.png"},
    {name: "Naprawy", link: "/repairs", photo: "https://cdn-icons-png.flaticon.com/512/3166/3166130.png"},
    {name: "Sprzęty", link: "/appliances", photo: "https://cdn-icons-png.flaticon.com/512/3159/3159543.png"},
    {name: "Części", link: "/parts", photo: "https://cdn-icons-png.flaticon.com/512/4350/4350670.png"}
  ];

  constructor(private connection: ConnectionService) { }


}
