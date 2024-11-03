# main-module
git submoduleを使用した複数リポジトリのビルド検証を行うためのリポジトリ

# 検証したい内容
OpenAPI Specからコードを自動生成し利用するプロジェクトにOpenAPI SpecのリポジトリをSubmoduleとして取り込み、コード自動生成が行えることを確認する
- OpenAPI SpecとそのOpenAPI Specで自動生成されたコードを利用するリポジトリを別々に管理したい。
- OpenAPI Specを管理するリポジトリはOpenAPI Specから自動生成するコードを利用するリポジトリの影響を受けないことが望ましい。
- OpenAPI Specを利用するリポジトリは自動生成するコードはそのリポジトリ内で自由に変更が出来ることが望ましい。

## 検証観点

- main ブランチ同士でビルドすることが出来る
- main module = featureブランチ、sub module = mainブランチでビルドすることが出来る
- main module = featureブランチ、sub module = featureブランチでビルドすることが出来る

# Git Submoduleの使い方

1. Submoduleを追加する

`.gitmodule`にsubmoduleを追加する

```bash

$ git submodule add  git@github.com:gallinago1981/sub-module.git sub-module

```

1. Submoduleを取り込む(更新する)

2. submoduleを以下のコマンドを使用して取り込む

``` bash
$ git submodule update --init --recursive

```
