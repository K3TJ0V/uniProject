import { Component, inject, OnInit, signal } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private fb = inject(FormBuilder);



  private mock: Data[] = [
    {
      header: "name",
      items: [
        {
          name: "test",
          test: "test"
        }
      ]
    },
    {
      header: "name2",
      items: [
        {
          name: "test123",
          test: "testasdf"
        },
         {
          name: "test123",
          test: "testasdfasdadadasd"
        },
      ]
    },
  ]

  protected formArrays: Record<string, FormArray<FormGroup>> = {};

  constructor() {
    this.mock.forEach(el => {
      const formArray = this.fb.array<FormGroup>([]);

      el.items.forEach(item => {
        const group = this.fb.group({
          [item.name] : [item.test]
        })
        formArray.push(group);
      })
      this.formArrays[el.header] = formArray;
    })
    
    this.getArrayGroups('name2').forEach(group => {
      console.log(group.controls['test123'].value);
    })
  }

  private getArrayGroups(name : string){
    return this.formArrays[name].controls;
  }
}

interface Data {
  header: string;
  items: {
    name: string,
    test: string
  }[]
} 