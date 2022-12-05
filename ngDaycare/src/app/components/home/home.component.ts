import { Component, OnInit } from '@angular/core';
import { NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [NgbCarouselModule, NgIf],
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


    images = ['assets/exterior.png', `assets/Daycareint.jpeg`, `assets/selfportraits.jpeg`];
  }


