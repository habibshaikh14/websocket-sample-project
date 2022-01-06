import { Component } from '@angular/core';
import { WebSocketService } from './web-socket.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebSocketService]
})
export class AppComponent {
  constructor (private websocketService: WebSocketService) {}
  greeting = "empty";

  ngOnInit() {
    this.websocketService.message.subscribe((message: string) => {
      this.greeting = message;
    })
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  connect(){
    this.websocketService._connect();
  }

  disconnect(){
    this.websocketService._disconnect();
  }
}
