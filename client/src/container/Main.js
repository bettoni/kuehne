import React, { Component } from 'react';
import KNlogo from '../assets/KN-logo.png';
import kn from '../assets/kn.png';
import '../css/App.css';
import Person from './people/Person'

import callGet from './people/PeopleAPI'

import { Navbar, Container, CardDeck } from 'reactstrap';


class Main extends Component {
  state = {
    isLoading: true,
    people: []
  };
  async componentDidMount() {
    await callGet().then((result) => {
      const body = result;
      this.setState({ people: body, isLoading: false });
    },
      (error) => {
        this.setState({
          isLoading: false,
          error
        });
      }
    );
  }
  render() {
    const { people, isLoading } = this.state;

    return (
      <div className="Main">
        <Navbar color="light" light expand="md">
          < img className="picture" src={kn} height="50" alt="" />
          Contact list
        </Navbar>

        <div class="container">
          <div class="row justify-content-md-center">

            {people.map(person => {
              console.log(person)
              return (
                <div class="col-3">
                  <Person data={person} />
                </div>
              );
            }
            )}

          </div>
        </div >

      </div >
    );
  }
}

export default Main;
