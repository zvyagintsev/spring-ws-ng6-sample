import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {StompService, StompConfig} from '@stomp/ng2-stompjs';

import { AppComponent } from './app.component';
//import { StompConfig } from './StompConfig';

const stompConfig: StompConfig = {

  url: 'ws://127.0.0.1:8080/socket',

  headers: {
  },


  heartbeat_in: 0,
  heartbeat_out: 20000,


  reconnect_delay: 5000,

  debug: true
};

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule

  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
