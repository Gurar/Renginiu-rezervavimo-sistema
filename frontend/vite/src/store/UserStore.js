class UserStore {
    constructor() {
        this._isAuth = false
        this._user = '';
        this._accessToken = null;
        this._expireTime = null;
    }

    setIsAuth(bool) {
        this._isAuth = bool;
    }
    setUser(user) {
        this._user = user;
    }

    setAccessToken(string) {
        this._accessToken = string;
    }

    setExpireTime(int) {
        this._expireTime = int;
    }

    getIsAuth() {
        return this._isAuth;
    }

    getUser() {
        return this._user;
    }

    getAccessToken() {
        return this._accessToken;
    }

    getExpireTime() {
        return this._expireTime;
    }
}

export default new UserStore();