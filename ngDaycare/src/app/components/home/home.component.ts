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


    images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  }


