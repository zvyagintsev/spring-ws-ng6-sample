import { Component } from '@angular/core';

import {StompService} from '@stomp/ng2-stompjs';

import {Message} from '@stomp/stompjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  words = null;
  constructor(private stompService: StompService) {

    this.stompService.subscribe('/all').subscribe(data => {
      this.words = new Array<string>();
      JSON.parse(data.body).forEach(element => {
        this.words.push(element);
      });
    });
  }

  addWord(word) {
    this.stompService.publish('/app/add', word);
  }
}
