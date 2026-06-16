import styled, {createGlobalStyle} from 'styled-components';

const GlobalStyle = createGlobalStyle`
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        text-decoration: none;
    }

    html, body {
	    height: 100%;   
    }

    html {
        font-size: 10px;
        scroll-behavior: smooth;
        font-family: "Mulish",sans-serif;
        -webkit-font-smoothing: antialiased;
    }

    body {
        font-size: 1rem;
        color: #111;
    }
`;


export const Wrapper = styled.section`
    width: 100%;
    ${({height}) => (height ? `height: ${height};` : '')}
    ${({paddingTop}) => (paddingTop ? `padding-top: calc(${paddingTop} / 1.619);` : '')}
    ${({paddingRight}) => (paddingRight ? `padding-right: calc(${paddingRight} / 1.619);` : '')}
    ${({paddingBottom}) => (paddingBottom ? `padding-bottom: calc(${paddingBottom} / 1.619);` : '')}
    ${({paddingLeft}) => (paddingLeft ? `padding-left: calc(${paddingLeft} / 1.619);` : '')}      
    
    @media screen and (min-width: 992px) {
        ${({paddingTop}) => (paddingTop ? `padding-top: ${paddingTop};` : '')}
        ${({paddingRight}) => (paddingRight ? `padding-right: calc(${paddingRight});` : '')}
        ${({paddingBottom}) => (paddingBottom ? `padding-bottom: ${paddingBottom};` : '')}
        ${({paddingLeft}) => (paddingLeft ? `padding-left: calc(${paddingLeft});` : '')}      
    }
`;

export const Container = styled.div`
    margin-right: auto;
    margin-left: auto;
    ${({height}) => (height ? `height: ${height};` : '')}
    padding-top: ${({paddingTop}) => paddingTop || '0' };
    padding-right: ${({paddingRight}) => paddingRight || '15px' };
    padding-bottom: ${({paddingBottom}) => paddingBottom || '0' };
    padding-left: ${({paddingLeft}) => paddingLeft || '15' };
    

    @media screen and (min-width: 576px) {
        max-width: 540px;
    }

    @media screen and (min-width: 768px) {
        max-width: 720px;
    }

    @media screen and (min-width: 992px) {
        max-width: 960px;
    }

    @media screen and (min-width: 1200px) {
        max-width: ${({width}) => width || '1140px'}
    }

`;

export const Flex = styled.div`
    ${({height}) => height ?  `height: ${height};` : ''}
    display: flex;
    flex-direction: ${({direction}) => direction || 'row'};
    justify-content: ${({justifyContent}) => justifyContent || 'stretch'};
    align-items: ${({alignItems}) => alignItems || 'stretch'};
    ${({margin}) => (margin ? `margin: ${margin};` : '')}
    ${({padding}) => (padding ? `padding: ${padding};` : '')}
`;



export const Row = styled.div`
    width: 100%;
    height: 100%;
    display: grid;
    ${({gap}) => (gap ? `gap: ${gap};` : '')}
    ${({margin}) => (margin ? `margin: ${margin};` : '')}
    ${({padding}) => (padding ? `padding: ${padding};` : '')}
    ${({alignItems}) => (alignItems ? `align-items: ${alignItems};` : '')}
    ${({justifyItems}) => (justifyItems? `justify-items: ${justifyItems};` : '')}
    @media screen and (min-width: 768px) {
        grid-template-columns: repeat(auto-fit, minmax(min(10rem, 100%), 1fr));    
    }
`;

export const Column = styled.div`
    ${({width}) => (width ? `max-width: ${width};` : '')}
    ${({column}) => (column ? `grid-column: span ${column};` : '')}
`;

export const Button = styled.button`
    border: none;
    background-color: ${({background}) => background || 'transparent'};
    white-space: nowrap;
    outline: none;
    border: none;
    cursor: pointer;
`;
export default GlobalStyle;