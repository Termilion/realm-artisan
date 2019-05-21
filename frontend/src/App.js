import React, {Component} from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Groups from "./entities/Groups";
import Characters from "./entities/Characters";

// Basic Router
class App extends Component {
  render() {
    return(
        <Router>
            <Switch>
                <Route path='/' exact={true} component={Home}/>
                <Route path='/groups' exact={true} component={Groups}/>
                <Route path='/char' exact={true} component={Characters}/>
            </Switch>
        </Router>
    );
  }
}

export default App;
