import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-product-form',
  styleUrl: './product-form.css',
  templateUrl: './product-form.html',
})
export class ProductForm {

    productForm: FormGroup;

    constructor() {
        this.productForm = new FormGroup({
            id: new FormControl(null, []),
            name: new FormControl(null, [Validators.required]),
            description: new FormControl(null, [Validators.required]),
            price: new FormControl(null, [Validators.required, Validators.min(0)]),
            category: new FormControl(null, [Validators.required]),
            image: new FormControl(null, [Validators.required]),
            active: new FormControl(null, [Validators.required]),
        });
    }

    getDataForm() {
        let product = this.productForm.value;
        product.id = -1;
        //Llamar al servicio
        this.productForm.reset();
    }
}
