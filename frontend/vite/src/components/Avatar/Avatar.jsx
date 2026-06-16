import React from 'react';
import { 
    AvatarWrapper,
    AvatarCircle, 
    AvatarImage, 
    AvatarName 
} from './Avatar.elements';

const Avatar = ({ size, src, name}) => {
    return (
        <>
            <AvatarWrapper>
                <AvatarCircle size={size}>
                    { src ? <AvatarImage src={src} /> : ''}
                </AvatarCircle>
                <AvatarName>{name}</AvatarName>
            </AvatarWrapper>    
        </>
    )
}

export default Avatar;