import { EventEmitter, Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
    providedIn: 'root',
})
export class WebSocketService {
    webSocketEndPoint: string = 'http://localhost:8080/socket';
    stompClient: any;

    public message = new EventEmitter<string>();

    constructor() {}

    _connect() {
        console.log('Initialize WebSocket Connection');

        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect(
            {},
            _this.stompClient.ws.addEventListener('message', function (event: any) {
                _this.onMessageReceived(event.data);
            }),
            this.errorCallBack
        );
    }

    _disconnect() {
        if (this.stompClient) {
            this.stompClient.disconnect();
            console.log('Disconnected');
        }
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error: string) {
        console.log('errorCallBack -> ' + error);
        setTimeout(() => {
        this._connect();
        }, 5000);
    }

    _send(message: any) {
        console.log('calling logout api via web socket');
        this.stompClient.send('/app/greetings', {}, message);
    }

    onMessageReceived(message: string) {
        console.log('Message Recieved from Server :: ' + message);
        this.message.emit(message);
    }
}
