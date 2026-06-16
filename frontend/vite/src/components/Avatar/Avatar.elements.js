import styled from 'styled-components';

export const AvatarWrapper = styled.div`
    display: flex;
    align-items: center;
`;

export const AvatarCircle = styled.div`
    border-radius: 50%;
    height: ${({size}) => size || '36px'};
    width: ${({size}) => size || '36px'};
    background: ${({img}) => (img ? 'transpared' : '#777')};
    position: relative;
`;

export const AvatarImage = styled.img`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
`;

export const AvatarName = styled.span`
    display: inline-block;
    margin-left: 6px;
    font-size: 1.4rem;
    font-weight: 600;
    ::first-letter {
        text-transform: uppercase;
    }
`;