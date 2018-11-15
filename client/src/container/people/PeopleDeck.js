import React, { Component } from 'react';
import callGet from './PeopleAPI'
import Person from './Person'


class PeopleDeck extends Component {
    state = {
        isLoading: true,
        isError: false,
        actualPage: 1,
        actualSize: 8,
        people: [],

    };

    _callApi(newPage, actualSize, nameFilter) {
        callGet(newPage, actualSize, nameFilter).then((result) => {
            const body = result;
            this.setState({ people: body, isLoading: false });
        },
            (error) => {
                this.setState({
                    isLoading: false,
                    isError: true,
                    error
                });
            }
        );
    }


    componentDidMount() {
        this._callApi(this.state.actualPage, this.state.actualSize);
    }

    componentDidUpdate(prevProps) {
        console.log(this.props.nameFilter);
        if (this.props.newPage !== prevProps.newPage || this.props.nameFilter !== prevProps.nameFilter) {
            this._callApi(this.props.newPage, this.state.actualSize, this.props.nameFilter);
        }
    }

    render() {
        if (this.state.isLoading) {
            return <div>loading...</div>;
        }

        if (this.state.isError) {
            return <div>error</div>;
        }

        const people = this.state.people;

        return (
            <div class="card-deck deck-fluid" >
                {people.map(person => {
                    return (
                        <div class="col-3 mt-3">
                            <Person data={person} />
                        </div>
                    );
                }
                )}
            </div>
        )
    }
}

export default PeopleDeck;