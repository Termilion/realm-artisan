import React, { Component } from 'react';
import './App.css';
import AppNavbar from "./AppNavbar";
import logo from "./logo.png";
import { Link } from 'react-router-dom';
import { Button, Container, Spinner, Row, Col, ButtonGroup} from 'reactstrap';

// Homepage
// Goals: Summary, Shortcuts

class Home extends Component {

    // init state
    state = {
        isLoading: true,
        groups: [],
        chars: [],
        regions: []
    };

    // fetch data from backend
    async componentDidMount() {
        const grp_response = await fetch('/groups');
        const char_response = await fetch('/characters');
        const region_response = await fetch('/regions');
        const grp_body = await grp_response.json();
        const char_body = await char_response.json();
        const region_body = await region_response.json();
        this.setState({groups: grp_body, chars: char_body, regions: region_body, isLoading: false});
    }

    render() {
        const {groups, chars, regions, isLoading} = this.state;

        // display loading circle

        if (isLoading) {
            return <Spinner color="primary" />;
        }

        return (
            <div className="App">
                <AppNavbar/>
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <br/>
                    <h2>Currently part of the Realm</h2>
                    <br/>
                    <Container>
                        <Row>
                            <Col>Regions: {regions.length}</Col>
                            <Col>Groups: {groups.length}</Col>
                            <Col>Characters: {chars.length}</Col>
                        </Row>
                        <Row>
                            <Col>
                                <ButtonGroup>
                                    <Button color="secondary" tag={Link} to={"/region"}>View</Button>
                                    <Button color="success" tag={Link} to={"/region/new"}>Create</Button>
                                </ButtonGroup>
                            </Col>
                            <Col>
                                <ButtonGroup>
                                    <Button color="secondary" tag={Link} to={"/groups"}>View</Button>
                                    <Button color="success" tag={Link} to={"/groups/new"}>Create</Button>
                                </ButtonGroup>
                            </Col>
                            <Col>
                                <ButtonGroup>
                                    <Button color="secondary" tag={Link} to={"/char"}>View</Button>
                                    <Button color="success" tag={Link} to={"/char/new"}>Create</Button>
                                </ButtonGroup>
                            </Col>
                        </Row>
                    </Container>
                </header>
            </div>
        );
    }
}

export default Home;