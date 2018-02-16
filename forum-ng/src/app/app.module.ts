import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { ForumViewComponent } from './forum-view/forum-view.component';
import { DataService } from "./data-service.service";
import { HttpClientModule } from "@angular/common/http";
import { TopicViewComponent } from './topic-view/topic-view.component';

@NgModule({
  declarations: [
    AppComponent,
    ForumViewComponent,
    TopicViewComponent
  ],
  imports: [
    BrowserModule, HttpClientModule
  ],
  providers: [
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
