import { useEffect, useState } from "react";
import { Map, MapMarker } from "react-kakao-maps-sdk";

// kakao maps
const MapComponent = () => {
    const [currentLocation, setCurrentLocation] = useState({ lat: 37.5665, lng: 126.9780 });
    const [status, setStatus] = useState('');

    useEffect(() => {
        const geoFindMe = () => {
            setStatus('위치 파악 중…');
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const latitude = position.coords.latitude;
                    const longitude = position.coords.longitude;

                    setCurrentLocation({ lat: latitude, lng: longitude });
                    setStatus('');
                },
                () => {
                    setStatus('현재 위치를 가져올 수 없음');
                }
            );
        };

        geoFindMe();
    }, []);

    return (
        <div>
            <p id="status">{status}</p>
            {currentLocation.lat !== null && currentLocation.lng !== null ? (
                <Map
                    center={currentLocation}   // 현재 위치를 중심으로 지도 표시
                    style={{ width: '800px', height: '600px' }} // 지도 크기
                    level={3}                                   // 지도 확대 레벨
                >
                    <MapMarker position={currentLocation} />
                </Map>
            ) : null}
        </div>
    );
}

export default MapComponent;