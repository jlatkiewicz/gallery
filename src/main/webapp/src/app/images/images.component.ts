import {Component, OnInit} from '@angular/core';
import {Image} from "../image";
import {ImageService} from "../image.service";
import {ToastrService} from "ngx-toastr";


@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

  images: Image[];

  constructor(private imageService: ImageService,
              private toastr: ToastrService) {
    toastr.toastrConfig.timeOut = 1500;
  }

  ngOnInit() {
    this.getImages()
  }

  add(imageLink: string): void {
    const trimmedLink = imageLink.trim();
    if (trimmedLink != "") {
      this.imageService.addImage(trimmedLink)
        .subscribe(id => this.images.push({imageId: id, imageLink: trimmedLink}));
    }
    else {
      this.toastr.warning("You've tried to insert an empty link", "Warning");
    }
  }

  delete(image: Image): void {
    this.imageService.deleteImage(image.imageId)
      .subscribe(id => this.images = this.images.filter(i => i.imageId != id));
  }

  onError(image: Image): void {
    this.delete(image);
    this.toastr.error("There was a problem with one of the images.", "Error");
  }

  private getImages() {
    this.imageService.getImages()
      .subscribe(images => this.images = images)
  }
}
