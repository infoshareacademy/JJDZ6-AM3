module.exports = {
    indexPath: 'index.ftl',
    assetsDir: 'static',
    chainWebpack: (config) => {
        config
            .plugin('html')
            .tap(args => {
                args[0].meta = { baseUrl: `[=baseUrl]` };
                return args;
            })
    },
    devServer: {
        port: 8081,
        proxy: {
            '/rest': {
                target: 'http://localhost:8080',
            }
        }
    }
};
