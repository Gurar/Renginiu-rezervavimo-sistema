import React from 'react';
import { createRoot } from 'react-dom/client';
import store from './app/store';
import { 
  BrowserRouter, 
  Routes, 
  Route
} from "react-router-dom";
import { Provider } from 'react-redux';
import App from './App.jsx'
import GlobalStyle from './globalStyle';

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <Provider store={store}>
    <GlobalStyle/>
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<App/>}/>
      </Routes>  
    </BrowserRouter>
  </Provider>
);
