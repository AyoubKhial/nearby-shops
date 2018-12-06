import { Component, OnInit } from '@angular/core';
import { ShopsService } from 'src/app/services/shops.service';
import { Shop } from '../../models/shop';

@Component({
    selector: 'app-shops-list',
    templateUrl: './shops-list.component.html',
    styleUrls: ['./shops-list.component.css']
})
export class ShopsListComponent implements OnInit {

    public shops: Shop[] = []

    constructor(private shopsService: ShopsService) { }

    ngOnInit() {
       this.listShopsSortedByDistance();
    }

    listShopsSortedByDistance() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.shopsService.getAllShops(position.coords).subscribe(response => {
                    this.shops = response.content;
                });
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

}
