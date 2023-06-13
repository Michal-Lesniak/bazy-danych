import { Component, OnInit } from '@angular/core';
import { ConnectionService } from '../services/connection.service';
import { MatDialog } from "@angular/material/dialog";
import { Part } from '../interfaces/part';
import { NewPartComponent } from './new-part/new-part.component';
import { take } from 'rxjs';


@Component({
  selector: 'app-part',
  templateUrl: './part.component.html',
  styleUrls: ['./part.component.scss']
})
export class PartComponent implements OnInit{
  parts: Part[] = [];
  selectedItem: Part | null = null;
  searchText = '';
  public messageStateError = false;
  public messageStateComplete = false;
  public message = "";

  constructor(private connection: ConnectionService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getParts();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.parts = this.parts.filter(el => {
      return el.name.toLowerCase().includes(filterValue);
    });
  }

  deletePart(part: Part) {
    this.connection.deletePart(part.partCode).subscribe((res) => {
      if (res) {
        this.handleMessage(false, "Pomyślnie Usunięto!");
        this.getParts();
        this.selectedItem = null;
      }
    }, () => this.handleMessage(true, "Wystąpił błąd w trakcie usuwania!"))
  };

  getParts() {
    this.connection.getParts().pipe(take(1)).subscribe(
      data => this.parts = data
    )
  }

  public openDialog(): void {
    const newPartRef = this.dialog.open(NewPartComponent, {
      backdropClass: 'backdropDialog',
    });

    newPartRef.afterClosed().subscribe(result => {
      this.getParts()
      if(result === true){
        this.handleMessage(false, "Urządzenie zostało pomyślnie dodane.")
      }else if(result === false){
        this.handleMessage(true, "Urządzenie o podanym kodzie już istnieje.")
      }
      
    });
  }

  handleMessage(error: boolean, message: string) {
    if (error) {
      this.messageStateError = true;
    } else {
      this.messageStateComplete = true;
    }
    this.message = message;
    setTimeout(() => {
      this.messageStateError = false;
      this.messageStateComplete = false;
    }, 3000)
  }
}
