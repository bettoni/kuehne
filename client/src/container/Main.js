import React, { Component } from 'react';
import kn from '../assets/kn.png';
import '../css/App.css';
import PeopleDeck from './people/PeopleDeck'

import { Navbar } from 'reactstrap';

class Main extends Component {

  constructor(props) {
    super(props);

    this.state = {
      actualPage: 1,
      value: '',
      newValue: ''
    };

    this.nextPage = this.nextPage.bind(this);
    this.prevPage = this.prevPage.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.submit = this.submit.bind(this);
    this.handleKeyPress = this.handleKeyPress.bind(this);
  }


  nextPage() {
    this.setState({ actualPage: this.state.actualPage + 1 });
  }

  prevPage() {
    this.setState({ actualPage: this.state.actualPage - 1 });
  }

  handleChange(event) {
    this.setState({ value: event.target.value });
  }

  handleKeyPress(event) {
    if (event.key === 'Enter') {
      this.submit();
    }
  }

  submit() {
    this.setState({ newValue: this.state.value, actualPage: 1 })
  }

  render() {

    return (
      <div className="Main">
        <Navbar color="light" light expand="md">
          < img className="picture" src={kn} height="50" alt="" />
          Contact list
        </Navbar>

        <div class="container">

          <div class="row ">
            <div class="card-body row no-gutters align-items-center justify-content-between">
              <div class="col">

                <input class="form-control form-control-lg form-control-borderless"
                  type="search"
                  placeholder="Search"
                  onKeyPress={this.handleKeyPress}
                  value={this.state.valuex}
                  onChange={this.handleChange} />
              </div>

              <div class="col-3">
                <button class="btn btn-lg btn-success" type="submit" onClick={this.submit} >Search</button>
              </div>

              <div class="col-3">
                <input class="btn btn-secondary btn-fluid" type="button" value="previous page" disabled={this.state.actualPage === 1} onClick={this.prevPage} />
                <input class="btn btn-secondary btn-fluid" type="button" value="previous page" onClick={this.nextPage} />
              </div>
            </div>
          </div>
          <div class="row">
            <PeopleDeck newPage={this.state.actualPage} nameFilter={this.state.newValue} />
          </div>
        </div >
      </div >
    );
  }
}

export default Main;
